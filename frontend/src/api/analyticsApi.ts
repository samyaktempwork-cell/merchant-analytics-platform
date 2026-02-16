import axios from "axios";

const API_BASE = import.meta.env.VITE_API_BASE;

export const fetchMTDSummary = (params?: any) => {
  return axios.get(`${API_BASE}/summary/mtd`, { params });
};

export const fetchMonthlySummary = (params?: any) => {
  return axios.get(`${API_BASE}/summary/monthly`, { params });
};
