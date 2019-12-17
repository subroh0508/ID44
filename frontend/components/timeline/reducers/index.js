import { combineReducers } from "redux";
import ownAccounts from "./ownAccounts";
import timelines from "./timelines";

export default combineReducers({ ownAccounts, timelines });
