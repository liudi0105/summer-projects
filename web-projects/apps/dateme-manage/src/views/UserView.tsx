import { CrudTable } from "@common-module/common-antd";
import { container } from "../container";
import { UserAccountService } from "../servcies/UserService";

const userService = container.get(UserAccountService);

export const UserView = () => {
  // const [data, setData] = useState<AppPageResult<UserAccountEntity>>();

  // useEffect(() => {
  //   userService
  //     .listPaged({
  //       pageIndex: 1,
  //       pageSize: 10,
  //     })
  //     .then(setData);
  // }, []);

  return (
    <div>
      <CrudTable
        service={userService}
        // dataSource={data?.content ?? []}
        columns={[
          {
            dataIndex: "username",
            title: "Username",
            create: true,
          },
          {
            dataIndex: "email",
            title: "Email",
            create: true,
          },
        ]}
      />
    </div>
  );
};
