import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";
import { commonHeader } from "../utils";

export const eventReducerName = "eventApi";

export const eventApi = createApi({
  reducerPath: eventReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL, prepareHeaders: commonHeader }),
  refetchOnMountOrArgChange: true,
  endpoints: (builder) => ({
    getEventList: builder.query({
      query: () => ({
        url: "/event/all",
        method: "GET",
      }),
    }),
    getEventDetails: builder.query({
      query: (id) => ({
        url: `/event/details/${id}`,
        method: "GET",
      }),
    }),
    addEvent: builder.mutation({
      query: (payload) => ({
        url: `/event/create`,
        method: "POST",
        body: payload,
      }),
    }),
    deleteEvent: builder.mutation({
      query: (id) => ({
        url: `/event/delete/${id}`,
        method: "DELETE",
      }),
    }),
    updateEvent: builder.mutation({
      query: (payload) => ({
        url: `/event/update`,
        method: "POST",
        body: payload,
      }),
    }),
    registerEvent: builder.mutation({
      query: ({ eventId, userId }) => ({
        url: `/event/register/${eventId}/${userId}`,
        method: "GET",
      }),
    }),
  }),
});

export const selectEvent = (state) => state?.[eventReducerName];

export const {
  useGetEventListQuery,
  useGetEventDetailsQuery,
  useAddEventMutation,
  useDeleteEventMutation,
  useUpdateEventMutation,
  useRegisterEventMutation,
} = eventApi;
