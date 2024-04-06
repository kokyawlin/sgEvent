import { DATETIME_FORMAT } from "../constants";
import dayjs from "dayjs";

export const getFormattedTime = (timestamp) => {
  return dayjs(timestamp).format(DATETIME_FORMAT);
};

export const toBase64 = (file, callback) => {
  var reader = new FileReader();
  reader.onloadend = function (e) {
    callback(e.target.result, e.target.error);
  };
  reader.readAsDataURL(file);
};
