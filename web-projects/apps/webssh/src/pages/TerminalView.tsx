import { Ant } from "@common-module/common-antd";
import { styled } from "@common-module/common-react";
import { createRef, useEffect, useState } from "react";
import { type ServerEntity, ServerService } from "../services/index.ts";
import { openTerminal } from "../util/terminal.ts";

const STerminalBox = styled.div``;

const SExplore = styled.div`
  width: 200px;
  margin-right: 8px;
  border: 4px solid #888;
`;

const SExploreItem = styled.div`
  height: 24px;
  background-color: #000;
  color: #fff;
  margin-bottom: 2px;
  border-collapse: collapse;
  cursor: pointer;
`;

export const ExploreItem = (
  props: {
    onOpen: (serverId: string) => void;
  } & ServerState
) => {
  return (
    <SExploreItem onClick={() => props.onOpen(props.id)}>
      {props.host}
    </SExploreItem>
  );
};

export interface ServerState extends ServerEntity {
  open: boolean;
  current: boolean;
}

const Explore = (props: { servers: ServerState[] }) => {
  return (
    <SExplore>
      {props.servers.map((v) => (
        <ExploreItem
          key={v.id}
          onOpen={(serverId) => {
            const s = props.servers.find((v) => v.id === serverId);
            if (s) {
              s.open = true;
            }
          }}
          {...v}
        />
      ))}
    </SExplore>
  );
};

const OpenTab = () => {
  return <div style={{ height: 28 }} />;
};

const serverService = new ServerService();

export const TerminalView = () => {
  const ref = createRef<HTMLDivElement>();

  const [servers, setServers] = useState<ServerState[]>([]);

  useEffect(() => {
    if (!ref.current) {
      throw new Error("box not ready");
    }

    openTerminal(
      {
        operate: "connect",
        host: "155.138.165.208", //IP
        port: 22, //端口号
        username: "root", //用户名
        password: "}D5rmJNJx.(Z3,KM", //密码
      },
      ref.current
    );

    serverService.listAll().then((v) => {
      const s = v as ServerState[];
      for (const v of s) {
        v.current = false;
        v.open = false;
      }
      setServers(s);
    });
  }, []);

  return (
    <Ant.Flex>
      <Explore servers={servers} />
      <Ant.Flex vertical style={{ width: "100%", height: "100%" }}>
        <OpenTab />
        <STerminalBox ref={ref} />
      </Ant.Flex>
    </Ant.Flex>
  );
};
