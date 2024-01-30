import { Fragment } from "react";
import ReactDOM from "react-dom/client";
import { createGlobalStyle } from 'styled-components';
import App from "./App";

const GlobalStyle = createGlobalStyle`
  html, body, #root {
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
  }
`;

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <Fragment>
    <GlobalStyle />
    <App />
  </Fragment>
);
