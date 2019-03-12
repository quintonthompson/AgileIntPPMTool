import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialState = {};
const middelware = [thunk];

let store;

if (window.navigator.userAgent.includes("Chrome")) {
  // ...middleWare allows us to add more middle ware than thunk in the future. It is e6 syntax
  store = createStore(
    rootReducer,
    initialState,
    compose(
      applyMiddleware(...middelware),
      window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__()
    )
  );
  // if this is not chrome
} else {
  store = createStore(
    rootReducer,
    initialState,
    compose(applyMiddleware(...middelware))
  );
}

export default store;
