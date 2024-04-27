import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";
import { commonHeader } from "../utils";

export const reviewReducerName = "reviewApi";

export const reviewApi = createApi({
  reducerPath: reviewReducerName,
  baseQuery: fetchBaseQuery({ baseUrl: baseURL, prepareHeaders: commonHeader }),
  refetchOnMountOrArgChange: true,
  endpoints: (builder) => ({
    getEventReviews: builder.query({
      query: (eventId) => ({
        url: `/review/event/${eventId}`,
        method: "GET",
      }),
    }),
    postEventReview: builder.mutation({
      query: ({ eventId, userId, rating, comment }) => ({
        url: `/review/add`,
        method: "POST",
        body: { eventId, userId, rating, comment },
      }),
    }),
  }),
});

export const {
  useGetEventReviewsQuery,
  usePostEventReviewMutation,
} = reviewApi;
