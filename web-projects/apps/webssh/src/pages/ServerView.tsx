import { CrudTable } from "@common-module/common-antd";
import { ServerService } from "../services";

const serverService = new ServerService();

export const ServerView = () => {
  return (
    <div>
      <CrudTable
        service={serverService}
        columns={[
          {
            dataIndex: "host",
            title: "Host",
          },
          {
            dataIndex: "port",
            title: "Port",
          },
          {
            dataIndex: "username",
            title: "Username",
          },
          {
            dataIndex: "password",
            title: "Password",
          },
          {
            dataIndex: "privateKey",
            title: "Private Key",
          },
        ]}
      />
    </div>
  );
};
