import type { SummaryResponse } from "../types";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
} from "recharts";

interface Props {
  data: SummaryResponse | null;
}

export default function MTDSummary({ data }: Props) {
  if (!data) return <div className="card">No data available</div>;

  const chartData = Object.entries(data.byCardBrand).map(
    ([brand, count]) => ({
      brand,
      count,
    })
  );

  return (
    <div className="card">
      <div className="stats-row" style={{ marginBottom: "20px" }}>
        <div className="stat-box">
          <h3>{data.totalTransactions}</h3>
          <div className="stat-label">Total Transactions</div>
        </div>

        <div className="stat-box">
          <h3>{data.totalApproved}</h3>
          <div className="stat-label">Approved</div>
        </div>

        <div className="stat-box">
          <h3>{data.totalDeclined}</h3>
          <div className="stat-label">Declined</div>
        </div>
      </div>

      {chartData.length > 0 && (
        <div style={{ width: "100%", height: 250 }}>
          <ResponsiveContainer>
            <BarChart data={chartData}>
              <XAxis dataKey="brand" />
              <YAxis />
              <Tooltip />
              <Bar dataKey="count" fill="#4f46e5" />
            </BarChart>
          </ResponsiveContainer>
        </div>
      )}
    </div>
  );
}
