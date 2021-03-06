import * as Actions from "../actions/timelines";

const initialState = {
  active: [],
  subscription: null,
  streams: {},
  focusTab: null,
};

const timelines = (state = initialState, action) => {
  switch (action.type) {
    case Actions.SET_SUBSCRIPTION:
      return {
        ...state,
        subscription: action.value,
      };
    case Actions.CLEAR_SUBSCRIPTION: {
      return {
        ...state,
        subscription: null,
      };
    }
    case Actions.ADD_ACTIVE_STREAM:
      return {
        ...state,
        active: [...state.active, action.value],
      };
    case Actions.REMOVE_ACTIVE_STREAM:
      return {
        ...state,
        active: state.active.filter(k => k === action.value),
      };
    case Actions.SET_FOCUS_TAB:
      return {
        ...state,
        focusTab: action.value,
      };
    case Actions.APPEND_STATUS:
      return {
        ...state,
        streams: {
          ...state.streams,
          [action.value]: [action.status, ...(state.streams[action.value] || [])],
        },
      };
    case Actions.UPDATE_STATUS:
      return {
        ...state,
        streams: Object.keys(state.streams).reduce((acc, key) => {
          const statuses = state.streams[key] || [];
          const ids = statuses.map(s => s.id);
          const index = ids.indexOf(action.value.id);

          if (index !== -1) {
            statuses.splice(index, 1, action.value);
          }

          return { ...acc, [key]: [...statuses] }
        }, {}),
      };
    case Actions.APPEND_STATUSES:
      return {
        ...state,
        streams: {
          ...state.streams,
          [action.value]: [...action.statuses, ...(state.streams[action.value] || [])],
        },
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
