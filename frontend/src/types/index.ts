export interface SummaryResponse {
  totalTransactions: number;
  totalApproved: number;
  totalDeclined: number;
  byCardBrand: Record<string, number>;
  byDeclineReason: Record<string, number>;
}
