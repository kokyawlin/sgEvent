import * as React from "react";
import { navigate } from "gatsby";

const IndexPage = () => {
  React.useEffect(() => {
    navigate("/login");
  }, []);
  return null;
};

export default IndexPage;
