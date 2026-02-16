import { useEffect, useState } from "react";
import { fetchMTDSummary, fetchMonthlySummary } from "./api/analyticsApi";
import type { SummaryResponse } from "./types/index";

function App() {
  const [mtd, setMtd] = useState<SummaryResponse | null>(null);
  const [monthly, setMonthly] = useState<Record<string, SummaryResponse>>({});
  const [filters, setFilters] = useState({
    cardBrand: "",
    status: "",
    declineReasonCode: ""
  });

  const loadData = async () => {
    const params = Object.fromEntries(
      Object.entries(filters).filter(([_, v]) => v !== "")
    );

    const mtdRes = await fetchMTDSummary(params);
    const monthlyRes = await fetchMonthlySummary(params);

    setMtd(mtdRes.data);
    setMonthly(monthlyRes.data);
  };

  useEffect(() => {
    loadData();
  }, []);

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>Merchant Analytics Dashboard</h1>

      <h2>Filters</h2>
      <div style={{ marginBottom: "20px" }}>
        <input
          placeholder="Card Brand"
          value={filters.cardBrand}
          onChange={(e) =>
            setFilters({ ...filters, cardBrand: e.target.value })
          }
        />
        <input
          placeholder="Status"
          value={filters.status}
          onChange={(e) =>
            setFilters({ ...filters, status: e.target.value })
          }
        />
        <input
          placeholder="Decline Code"
          value={filters.declineReasonCode}
          onChange={(e) =>
            setFilters({ ...filters, declineReasonCode: e.target.value })
          }
        />
        <button onClick={loadData}>Apply Filters</button>
      </div>

      <h2>Month-To-Date Summary</h2>
      {mtd && (
        <div>
          <p>Total: {mtd.totalTransactions}</p>
          <p>Approved: {mtd.totalApproved}</p>
          <p>Declined: {mtd.totalDeclined}</p>
        </div>
      )}

      <h2>Monthly Summary</h2>
      {Object.entries(monthly).map(([month, data]) => (
        <div key={month} style={{ marginBottom: "10px" }}>
          <h3>{month}</h3>
          <p>Total: {data.totalTransactions}</p>
          <p>Approved: {data.totalApproved}</p>
          <p>Declined: {data.totalDeclined}</p>
        </div>
      ))}
    </div>
  );
}

export default App;
