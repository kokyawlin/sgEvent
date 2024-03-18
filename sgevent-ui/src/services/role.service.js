import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";

export const roleReducerName = "roleApi";

export const roleApi = createApi({
  reducerPath: userReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL }),
  endpoints: (builder) => ({
    getRoleList: builder.query({
      query: () => ({
        url: "/eventrole/all",
        method: "GET",
      }),
    }),
  }),
});

export const selectRole = (state) => state?.[userReducerName];

export const { useGetRoleListQuery } = roleApi;
