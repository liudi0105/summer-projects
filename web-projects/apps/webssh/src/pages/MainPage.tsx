import { createRef, useEffect } from "react";
import { openTerminal } from "../util/terminal";


export const Main = () => {
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
    )
  }, []);

  return <div ref={ref}></div>;
};
