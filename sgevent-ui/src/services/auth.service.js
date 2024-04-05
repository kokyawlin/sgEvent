import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { baseURL } from "../constants";
import { setCredentials } from '../state/auth/slice'; 

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

      // 新增：当请求成功时，返回用户信息
      onQueryStarted: async (_, { dispatch, queryFulfilled }) => {
        try {
          const { data } = await queryFulfilled;
          // 直接使用后端返回的整个data对象作为userInfo
          dispatch(setCredentials({ userInfo: data, isLoggedIn: true }));
        } catch (error) {
          console.error("Login failed:", error);
        }
      },
      
    }),
    
    // 新增：处理注册请求
    signUp: builder.mutation({
      // Adjust the query to accept user info and pass them in the request body
      query: (userInfo) => ({
        url: "/eventuser/UserSignup", // 假设这是你的注册接口
        method: "POST",
        body: userInfo, // This will be the object containing the user info
      }),
    }),
  }),
});
 
export const selectAuth = (state) => state?.[authReducerName];
 
// 导出useLoginMutation和useSignUpMutation
export const { useLoginMutation, useSignUpMutation } = authApi;