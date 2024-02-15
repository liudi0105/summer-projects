import {
  FluentProvider,
  makeStyles,
  shorthands,
  tokens,
  webDarkTheme,
  webLightTheme
} from "@fluentui/react-components";
import styled from "styled-components";
import { Header } from "./Header";
import { SideBar } from "./SideBar";
import { Content } from "./Content";
import { useThemaStore } from "./stores";

const useStyles = makeStyles({
  button: {
    marginTop: "5px",
  },
  provider: {
  },
  text: {
    backgroundColor: tokens.colorBrandBackground2,
    color: tokens.colorBrandForeground2,
    fontSize: "20px",
  },
});

const SApp = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  #main {
    display: flex;
    flex-grow: 1;
    #side-bar {
      width: 200px;
      background-color: #ccffcc;
      flex-shrink: 0;
    }
  }
`

function App() {
  const styles = useStyles();

  const dark = useThemaStore(s => s.dark)

  return (
    <FluentProvider className={styles.provider} theme={dark ? webDarkTheme : webLightTheme}>
      <SApp id="app">
        <Header />
        <div id="main">
          <SideBar />
          <Content />
        </div>
      </SApp>
    </FluentProvider>
  );
}

export default App;
