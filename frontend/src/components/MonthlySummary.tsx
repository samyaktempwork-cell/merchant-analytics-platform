import type { SummaryResponse } from "../types";

interface Props {
  data: Record<string, SummaryResponse>;
}

export default function MonthlySummary({ data }: Props) {
  if (!data || Object.keys(data).length === 0)
    return <div className="card">No monthly data available</div>;

  return (
    <div>
      {Object.entries(data).map(([month, summary]) => (
        <div key={month} className="card" style={{ marginBottom: "20px" }}>
          <h3>{month}</h3>

          <div className="stats-row">
            <div className="stat-box">
              <h3>{summary.totalTransactions}</h3>
              <div className="stat-label">Total</div>
            </div>

            <div className="stat-box">
              <h3>{summary.totalApproved}</h3>
              <div className="stat-label">Approved</div>
            </div>

            <div className="stat-box">
              <h3>{summary.totalDeclined}</h3>
              <div className="stat-label">Declined</div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
