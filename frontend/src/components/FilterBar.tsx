import React from "react";

interface Props {
  filters: {
    cardBrand: string;
    status: string;
    declineReasonCode: string;
  };
  setFilters: React.Dispatch<
    React.SetStateAction<{
      cardBrand: string;
      status: string;
      declineReasonCode: string;
    }>
  >;
  onApply: () => void;
}

const FilterBar: React.FC<Props> = ({ filters, setFilters, onApply }) => {
  return (
    <div style={{ marginBottom: "20px" }}>
      <select
        value={filters.cardBrand}
        onChange={(e) =>
          setFilters({ ...filters, cardBrand: e.target.value })
        }
      >
        <option value="">All Card Brands</option>
        <option value="VISA">VISA</option>
        <option value="MASTERCARD">MASTERCARD</option>
        <option value="AMEX">AMEX</option>
      </select>

      <select
        value={filters.status}
        onChange={(e) =>
          setFilters({ ...filters, status: e.target.value })
        }
      >
        <option value="">All Status</option>
        <option value="APPROVED">APPROVED</option>
        <option value="DECLINED">DECLINED</option>
      </select>

      <input
        placeholder="Decline Code"
        value={filters.declineReasonCode}
        onChange={(e) =>
          setFilters({ ...filters, declineReasonCode: e.target.value })
        }
      />

      <button onClick={onApply}>Apply Filters</button>
    </div>
  );
};

export default FilterBar;
