import React from "react";
import { authSelector } from "../state/auth/slice";
import { useSelector } from "react-redux";
import { HOME_MAPPING } from "../constants";
import { navigate } from "gatsby";

export default function Home() {
  const { isLoggedIn, userInfo } = useSelector((state) => authSelector(state));
  React.useEffect(() => {
    //bring user to other pages based on their roleid
    const link =
      isLoggedIn && userInfo.roleId ? HOME_MAPPING[userInfo.roleId] : "/login";
    navigate(link);
  }, [isLoggedIn, userInfo]);
  return null;
}
