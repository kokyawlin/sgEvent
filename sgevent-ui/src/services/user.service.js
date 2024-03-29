import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";

export const userReducerName = "userApi";

export const userApi = createApi({
  reducerPath: userReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL }),
  endpoints: (builder) => ({
    getUserList: builder.query({
      query: () => ({
        url: "/eventuser/all",
        method: "GET",
      }),
    }),
    getUserDetails: builder.query({
      query: (userId) => ({
        url: `/eventuser/${userId}`,
        method: "GET",
      }),
    }),
    updateUser: builder.mutation({
      query: (payload) => ({
        url: `/eventuser/update`,
        method: "POST",
        body: payload,
      }),
    }),
  }),
});

export const selectUser = (state) => state?.[userReducerName];

export const {
  useGetUserListQuery,
  useGetUserDetailsQuery,
  useUpdateUserMutation,
} = userApi;
