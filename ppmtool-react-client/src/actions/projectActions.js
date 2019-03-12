import axios from "axios";
import { GET_ERRORS } from "./types";
import { connect } from "react-redux";
import AddProject from "../components/Project/AddProject";

//async returns a promise which javascript will wait until its finished and return result
export const createProject = (project, history) => async dispatch => {
  try {
    //wait for project to be posted and return to dashboard
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};
