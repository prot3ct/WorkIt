namespace WorkIt_Server.Models.DTO
{
    public class RegisterDTO
    {
        public string Email { get; set; }
        public string PassHash { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
    }
}