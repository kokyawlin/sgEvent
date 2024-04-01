import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";

export const eventReducerName = "eventApi";

export const eventApi = createApi({
  reducerPath: eventReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL }),
  refetchOnMountOrArgChange: true,
  endpoints: (builder) => ({
    getEventList: builder.query({
      query: () => ({
        url: "/eventuser/all",
        method: "GET",
      }),
    }),
    // getUserDetails: builder.query({
    //   query: (userId) => ({
    //     url: `/eventuser/${userId}`,
    //     method: "GET",
    //   }),
    // }),
    // updateUser: builder.mutation({
    //   query: (payload) => ({
    //     url: `/eventuser/update`,
    //     method: "POST",
    //     body: payload,
    //   }),
    // }),
    addEvent: builder.mutation({
      query: (payload) => ({
        url: `/event/create`,
        method: "POST",
        body: payload,
      }),
    }),
    // deleteUser: builder.mutation({
    //   query: (userId) => ({
    //     url: `/eventuser/delete/${userId}`,
    //     method: "DELETE",
    //   }),
    // }),
  }),
});

export const selectEvent = (state) => state?.[eventReducerName];

export const {
  useGetUserListQuery,
  //   useGetUserDetailsQuery,
  //   useUpdateUserMutation,
  useAddEventMutation,
  //   useDeleteUserMutation,
} = eventApi;
