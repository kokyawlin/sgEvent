import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";

export const authReducerName = "authApi";

export const authApi = createApi({
  reducerPath: authReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL }),
  endpoints: (builder) => ({
    login: builder.mutation({
      // Adjust the query to accept credentials and pass them in the request body
      query: (credentials) => ({
        url: "/eventuser/UserLogin",
        method: "POST",
        body: credentials, // This will be the object containing the email and password
      }),
    }),
  }),
});

export const selectAuth = (state) => state?.[authReducerName];

export const { useLoginMutation } = authApi;
