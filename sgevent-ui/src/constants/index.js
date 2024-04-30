export const baseURL = "http://13.250.39.99:8080/sgevent/v1";
export const mapURL = "https://www.onemap.gov.sg/api/common/elastic";



// local test
// export const baseURL = "http://localhost:8000/sgEvent/v1";
// export const mapURL = "http://localhost:8000/sgMap";

export const statusOptions = [
  { value: 1, label: "Active" },
  { value: 2, label: "Suspended" },
];
export const DATETIME_FORMAT = "DD/MM/YYYY HH:mm:ss";
export const MAP_TOKEN =
  "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzYTQ4MDNiNzM1Yzg4OWI5ZjBjOTI4NTMwYzgyNzg3MCIsImlzcyI6Imh0dHA6Ly9pbnRlcm5hbC1hbGItb20tcHJkZXppdC1pdC0xMjIzNjk4OTkyLmFwLXNvdXRoZWFzdC0xLmVsYi5hbWF6b25hd3MuY29tL2FwaS92Mi91c2VyL3Bhc3N3b3JkIiwiaWF0IjoxNzEyMTUxNzkwLCJleHAiOjE3MTI0MTA5OTAsIm5iZiI6MTcxMjE1MTc5MCwianRpIjoic3lpQ0poeTExSkgwWGRReCIsInVzZXJfaWQiOjMxNTAsImZvcmV2ZXIiOmZhbHNlfQ.hn916NAj4dHbuV4GKdOpHEUNUs2do7762PMJMiP3bVg";

export const HOME_MAPPING = {
  0: "/login",
  1: "/events",
  2: "/events",
  3: "/users",
};
