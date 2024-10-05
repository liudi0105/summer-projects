import { CrudTable } from "@common-module/common-antd";
import { AppPageResult } from "@common-module/common-types";
import { useEffect, useState } from "react";
import { container } from "../container";
import { UserAccountEntity, UserAccountService } from "../servcies/UserService";

const userService = container.get(UserAccountService);

export const UserView = () => {
  const [data, setData] = useState<AppPageResult<UserAccountEntity>>();

  const columns = [
    {
      dataIndex: "id",
      hidden: true,
    },
    {
      dataIndex: "username",
      title: "Username",
    },
    {
      dataIndex: "email",
      title: "Email",
    },
  ];

  useEffect(() => {
    userService
      .listPaged({
        pageIndex: 1,
        pageSize: 10,
      })
      .then(setData);
  }, []);

  return (
    <div>
      <CrudTable
        service={userService}
        dataSource={data?.content ?? []}
        columns={columns}
      />
    </div>
  );
};
