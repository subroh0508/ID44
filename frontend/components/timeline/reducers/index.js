import { combineReducers } from "redux";
import ownAccounts from "./ownAccounts";
import timelines from "./timelines";
import toots from './toots';

export default combineReducers({ ownAccounts, timelines, toots });
