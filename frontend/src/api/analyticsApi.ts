import axios from "axios";

const API_BASE = "http://localhost:8080/api";

export const fetchMTDSummary = (params?: any) => {
  return axios.get(`${API_BASE}/summary/mtd`, { params });
};

export const fetchMonthlySummary = (params?: any) => {
  return axios.get(`${API_BASE}/summary/monthly`, { params });
};
