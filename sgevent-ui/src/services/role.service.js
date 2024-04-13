import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";
import { commonHeader } from "../utils";

export const roleReducerName = "roleApi";

export const roleApi = createApi({
  reducerPath: roleReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL, prepareHeaders: commonHeader }),
  endpoints: (builder) => ({
    getRoleList: builder.query({
      query: () => ({
        url: "/eventrole/all",
        method: "GET",
      }),
    }),
  }),
});

export const selectRole = (state) => state?.[roleReducerName];

export const { useGetRoleListQuery } = roleApi;
