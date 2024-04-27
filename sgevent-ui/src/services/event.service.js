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
    
    // 正确
    getEventDetails: builder.query({
      query: (id) => ({
        url: `/event/details?eventid=${id}`,
        method: "GET",
      }),
    }),
    

    // getEventForEdit: builder.query({
    //   query: (id) => ({
    //     url: `/event/edit/${id}`,
    //     method: "GET",
    //   }),
    // }),
    
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
      query: ({ type, eventId, userId }) => ({
        url: `/event/${type}/${eventId}/${userId}`,
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
