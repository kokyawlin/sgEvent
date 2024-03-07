import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";

export const authReducerName = "authApi";

export const authApi = createApi({
  reducerPath: authReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL }),
  endpoints: (builder) => ({
    login: builder.mutation({
      query: () => ({
        url: "/login",
        method: "POST",
      }),
    }),
  }),
});

export const selectAuth = (state) => state?.[authReducerName];

export const { useLoginMutation } = authApi;
