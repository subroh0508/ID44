import * as Actions from "../actions/timelines";

const initialState = {
  subscriptions: {},
  statuses: {},
};

const timelines = (state = initialState, action) => {
  switch (action.type) {
    case Actions.SET_READY_FOR_SUBSCRIPTION:
      return {
        ...state,
        subscriptions: {
          ...state.subscriptions,
          [action.value]: action.subscription,
        }
      };
    case Actions.REMOVE_SUBSCRIPTION: {
      delete state.subscriptions[action.value];

      return { ...state };
    }
    case Actions.APPEND_STATUS:
      return {
        ...state,
        statuses: {
          ...state.statuses,
          [action.value]: [action.status, ...(state.statuses[action.value] || [])],
        }
      };
    default:
      return state;
  }
};

export default timelines;
