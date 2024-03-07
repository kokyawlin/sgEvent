import { createSlice } from "@reduxjs/toolkit";
import { authApi } from "../../services/auth.service";

const initialState = {
  isLoggedin: false,
  userInfo: {},
};

export const authSliceName = "authSlice";

// Slice
const authSlice = createSlice({
  name: authSliceName,
  initialState,
  reducers: {
    setLoginStatus: (state, action) => {
      const { payload } = action;
      state.isLoggedin = payload;
    },
  },
  extraReducers: (builder) => {
    builder.addMatcher(
      authApi.endpoints.login.matchFulfilled,
      (state, { payload }) => {
        state.isLoggedin = true;
        state.userInfo = payload.data;
      }
    );
  },
});

// Reducers
export default authSlice.reducer;

// Selectors
export const authSelector = (state) => state?.[authSliceName];

// Actions
// const { setLoginStatus } = authSlice.actions;
