import { GET_PROJECTS } from "../actions/types";

//initial state of this type of action is empty array or projects, and empty project element
const initialState = {
  projects: [],
  project: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload
      };

    //default will return initialState of empty array and element
    default:
      return state;
  }
}
