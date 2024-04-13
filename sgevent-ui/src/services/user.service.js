import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";
import { commonHeader } from "../utils";

export const userReducerName = "userApi";

export const userApi = createApi({
  reducerPath: userReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL, prepareHeaders: commonHeader }),
  refetchOnMountOrArgChange: true,
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
    addUser: builder.mutation({
      query: (payload) => ({
        url: `/eventuser/add`,
        method: "POST",
        body: payload,
      }),
    }),
    deleteUser: builder.mutation({
      query: (userId) => ({
        url: `/eventuser/delete/${userId}`,
        method: "DELETE",
      }),
    }),
  }),
});

export const selectUser = (state) => state?.[userReducerName];

export const {
  useGetUserListQuery,
  useGetUserDetailsQuery,
  useUpdateUserMutation,
  useAddUserMutation,
  useDeleteUserMutation,
} = userApi;
