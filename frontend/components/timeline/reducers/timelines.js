import * as Actions from "../actions/timelines";

const initialState = {
  active: {},
  inactive: {},
  streams: {},
};

const timelines = (state = initialState, action) => {
  switch (action.type) {
    case Actions.ACTIVATE_SUBSCRIPTION:
      return {
        ...state,
        active: {
          ...state.active,
          [action.value]: action.subscription,
        }
      };
    case Actions.INACTIVATE_SUBSCRIPTION: {
      const subscription = state.active[action.value];
      delete state.active[action.value];

      return {
        ...state,
        inactive: {
          ...state.inactive,
          [action.value]: subscription,
        },
      };
    }
    case Actions.CLEAR_INACTIVE_SUBSCRIPTION:
      return {
        ...state,
        inactive: {},
      };
    case Actions.CLEAR_ALL_SUBSCRIPTION: {
      return {
        ...state,
        active: {},
        inactive: {},
      };
    }
    case Actions.APPEND_STATUS:
      return {
        ...state,
        streams: {
          ...state.streams,
          [action.value]: [action.status, ...(state.streams[action.value] || [])],
        }
      };
    case Actions.CLEAR_STREAMS:
      return {
        ...state,
        streams: {},
      };
    default:
      return state;
  }
};

export default timelines;
