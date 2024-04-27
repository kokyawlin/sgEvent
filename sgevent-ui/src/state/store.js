import { configureStore, combineReducers } from "@reduxjs/toolkit";

import authReducer, { authSliceName } from "./auth/slice";
import { authApi, authReducerName } from "../services/auth.service";
import { userApi, userReducerName } from "../services/user.service";
import { roleApi, roleReducerName } from "../services/role.service";
import { eventApi, eventReducerName } from "../services/event.service";
import { mapApi, mapReducerName } from "../services/map.service";
import { reviewApi, reviewReducerName } from "../services/review.service";

import {
  persistStore,
  persistReducer,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
  key: "auth",
  storage,
  whitelist: [authSliceName],
};

const rootReducer = combineReducers({
  [authSliceName]: authReducer,
  [authReducerName]: authApi.reducer,
  [userReducerName]: userApi.reducer,
  [roleReducerName]: roleApi.reducer,
  [eventReducerName]: eventApi.reducer,
  [mapReducerName]: mapApi.reducer,
  [reviewReducerName]: reviewApi.reducer, // 添加 reviewReducer
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) => [
    ...getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
    authApi.middleware,
    userApi.middleware,
    roleApi.middleware,
    eventApi.middleware,
    mapApi.middleware,
    reviewApi.middleware, // 添加 reviewApi.middleware
  ],
});

export const persistor = persistStore(store);

export default store;
