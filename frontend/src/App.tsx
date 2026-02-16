import { useEffect, useState } from "react";
import { fetchMTDSummary, fetchMonthlySummary } from "./api/analyticsApi";
import type { SummaryResponse } from "./types";
import FilterBar from "./components/FilterBar";
import MTDSummary from "./components/MTDSummary";
import MonthlySummary from "./components/MonthlySummary";
import "./App.css";

function App() {
  const [mtd, setMtd] = useState<SummaryResponse | null>(null);
  const [monthly, setMonthly] = useState<Record<string, SummaryResponse>>({});
  const [filters, setFilters] = useState({
    cardBrand: "",
    status: "",
    declineReasonCode: "",
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const loadData = async () => {
    try {
      setLoading(true);
      setError("");

      const params = Object.fromEntries(
        Object.entries(filters).filter(([_, v]) => v !== "")
      );

      const mtdRes = await fetchMTDSummary(params);
      const monthlyRes = await fetchMonthlySummary(params);

      setMtd(mtdRes.data);
      setMonthly(monthlyRes.data);
    } catch (err) {
      setError("Failed to load analytics data.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  return (
    <div className="container">
      <h1>Merchant Analytics Dashboard</h1>

      <div className="section">
        <FilterBar
          filters={filters}
          setFilters={setFilters}
          onApply={loadData}
        />
      </div>

      {loading && <div className="card">Loading analytics...</div>}

      {error && <div className="error-card">{error}</div>}

      {!loading && !error && (
        <>
          <div className="section">
            <h2>Month-To-Date Summary</h2>
            <MTDSummary data={mtd} />
          </div>

          <div className="section">
            <h2>Monthly Summary</h2>
            <MonthlySummary data={monthly} />
          </div>
        </>
      )}
    </div>
  );
}

export default App;
