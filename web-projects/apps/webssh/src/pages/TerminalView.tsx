import { Ant } from "@common-module/common-antd";
import { styled } from "@common-module/common-react";
import { createRef, useEffect, useState } from "react";
import { openTerminal } from "../util/terminal.ts";
import { container } from "../container.ts";
import { ServerEntity, ServerService } from "../services/index.ts";
import { BaseEntity } from "@common-module/common-api";

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
  return <SExploreItem onClick={() => props.onOpen(props.id)}></SExploreItem>;
};

export interface ServerState extends ServerEntity {
  open: boolean;
  current: boolean;
}

const Explore = (props: { servers: ServerState[] }) => {
  const [servers, setServers] = useState<ServerState[]>(props.servers);

  return (
    <SExplore>
      {servers.map((v) => (
        <ExploreItem
          onOpen={(serverId) => {
            const s = servers.find((v) => v.id == serverId);
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
  return <div style={{ height: 28 }}></div>;
};

const serverService = container.get(ServerService);

export const TerminalView = () => {
  const ref = createRef<HTMLDivElement>();

  const [servers, setServers] = useState<ServerEntity[]>([]);

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

    serverService.listAll().then(setServers);
  }, []);

  return (
    <Ant.Flex>
      <Explore servers={servers} />
      <Ant.Flex vertical style={{ width: "100%", height: "100%" }}>
        <OpenTab />
        <STerminalBox ref={ref}></STerminalBox>
      </Ant.Flex>
    </Ant.Flex>
  );
};
