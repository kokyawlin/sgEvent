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
        url: "/event/all",
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
  }),
});

export const selectEvent = (state) => state?.[eventReducerName];

export const { useGetEventListQuery, useAddEventMutation } = eventApi;
