import { CrudTable } from "@common-module/common-antd";
import { UserService } from "../services";

const userService = new UserService();

export const UserView = () => {
  return (
    <div>
      <CrudTable
        service={userService}
        columns={[
          {
            dataIndex: "username",
            title: "Username",
          },
          {
            dataIndex: "password",
            title: "Password",
          },
        ]}
      />
    </div>
  );
};
