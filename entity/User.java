package by.htp.library.entity;

public class User {

		private int id_user;
		private String Type_user;
		private String Login;
		private String Password;
		private String Name_user;
		private String Surname_user;
		
		public User() {
			super();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((Login == null) ? 0 : Login.hashCode());
			result = prime * result + ((Name_user == null) ? 0 : Name_user.hashCode());
			result = prime * result + ((Password == null) ? 0 : Password.hashCode());
			result = prime * result + ((Surname_user == null) ? 0 : Surname_user.hashCode());
			result = prime * result + ((Type_user == null) ? 0 : Type_user.hashCode());
			result = prime * result + id_user;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (Login == null) {
				if (other.Login != null)
					return false;
			} else if (!Login.equals(other.Login))
				return false;
			if (Name_user == null) {
				if (other.Name_user != null)
					return false;
			} else if (!Name_user.equals(other.Name_user))
				return false;
			if (Password == null) {
				if (other.Password != null)
					return false;
			} else if (!Password.equals(other.Password))
				return false;
			if (Surname_user == null) {
				if (other.Surname_user != null)
					return false;
			} else if (!Surname_user.equals(other.Surname_user))
				return false;
			if (Type_user == null) {
				if (other.Type_user != null)
					return false;
			} else if (!Type_user.equals(other.Type_user))
				return false;
			if (id_user != other.id_user)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "User [id_user=" + id_user + ", Type_user=" + Type_user + ", Login=" + Login + ", Password="
					+ Password + ", Name_user=" + Name_user + ", Surname_user=" + Surname_user + "]";
		}

		public int getId_user() {
			return id_user;
		}

		public void setId_user(int id_user) {
			this.id_user = id_user;
		}

		public String getType_user() {
			return Type_user;
		}

		public void setType_user(String type_user) {
			Type_user = type_user;
		}

		public String getLogin() {
			return Login;
		}

		public void setLogin(String login) {
			Login = login;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getName_user() {
			return Name_user;
		}

		public void setName_user(String name_user) {
			Name_user = name_user;
		}

		public String getSurname_user() {
			return Surname_user;
		}

		public void setSurname_user(String surname_user) {
			Surname_user = surname_user;
		}
		
		
		

}
