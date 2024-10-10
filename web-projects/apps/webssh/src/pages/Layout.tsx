import { createRef, useEffect } from "react";
import { openTerminal } from "../util/terminal";
import { styled } from "@common-module/common-react";

const Sdiv = styled.div``;

export const Layout = () => {
  const ref = createRef<HTMLDivElement>();
  useEffect(() => {
    openTerminal(
      {
        operate: "connect",
        host: "",
        port: 22, //端口号
        username: "root", //用户名
        password: "",
      },
      ref.current!
    );
  }, []);

  return <Sdiv ref={ref}></Sdiv>;
};
