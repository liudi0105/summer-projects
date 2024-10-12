import { createRef, useEffect } from "react";
import { styled } from "@common-module/common-react";
import { openTerminal } from "../util/terminal.ts";
import React from "react";

const Sdiv = styled.div``;

export const Layout = () => {
  const ref = createRef<HTMLDivElement>();
  useEffect(() => {
    openTerminal(
      {
        operate: "connect",
        host: "155.138.165.208", //IP
        port: 22, //端口号
        username: "root", //用户名
        password: "}D5rmJNJx.(Z3,KM", //密码
      },
      ref.current!
    );
  }, []);

  return <Sdiv ref={ref}></Sdiv>;
};
