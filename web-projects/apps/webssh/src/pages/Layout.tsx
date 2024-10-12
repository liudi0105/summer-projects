import { Ant } from "@common-module/common-antd";
import { styled } from "@common-module/common-react";
import { createRef, useEffect } from "react";
import { openTerminal } from "../util/terminal.ts";

const STerminalBox = styled.div``;

const SExplore = styled.div`
  width: 200px;
`;

const SExploreItem = styled.div`
  height: 24px;
  background-color: #000;
  color: #fff;
  margin-bottom: 2px;
  border-collapse: collapse;
  cursor: pointer;
`;

export const ExploreItem = (props: {
  open: boolean
  current: boolean
}) => {
  return <SExploreItem></SExploreItem>;
};

const Explore = () => {
  return (
    <SExplore>
      <SExploreItem>10.128.29.1</SExploreItem>
      <SExploreItem>10.128.29.1</SExploreItem>
      <SExploreItem>10.128.29.1</SExploreItem>
      <SExploreItem>10.128.29.1</SExploreItem>
      <SExploreItem>10.128.29.1</SExploreItem>
      <SExploreItem>10.128.29.1</SExploreItem>
    </SExplore>
  );
};

const OpenTab = () => {
  return <div style={{ height: 28 }}></div>;
};

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

  return (
    <Ant.Flex>
      <Explore />
      <Ant.Flex vertical style={{ width: "100%", height: "100%" }}>
        <OpenTab />
        <STerminalBox ref={ref}></STerminalBox>
      </Ant.Flex>
    </Ant.Flex>
  );
};
