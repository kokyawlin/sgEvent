import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { mapURL, MAP_TOKEN } from "../constants";

export const mapReducerName = "mapApi";

export const mapApi = createApi({
  reducerPath: mapReducerName,
  baseQuery: fetchBaseQuery({
    baseUrl: mapURL,
    prepareHeaders: (headers) => {
      headers.set("authorization", `Bearer ${MAP_TOKEN}`);
      return headers;
    },
  }),
  refetchOnMountOrArgChange: true,
  endpoints: (builder) => ({
    searchLocation: builder.mutation({
      query: (input) => ({
        url: `/search?returnGeom=Y&getAddrDetails=Y&searchVal=${input}`,
        method: "GET",
      }),
    }),
  }),
});

export const selectMap = (state) => state?.[mapReducerName];

export const { useSearchLocationMutation } = mapApi;
