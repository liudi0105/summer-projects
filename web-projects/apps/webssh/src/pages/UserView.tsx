import { CrudTable } from "@common-module/common-antd";
import { container } from "../container";
import { UserService } from "../services";

const userService = container.get(UserService);

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
