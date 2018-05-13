using System;

namespace WorkIt_Server.Models.DTO
{
    public class TaskDTO
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public DateTime StartDate { get; set; }
        public int Length { get; set; }
        public string Description { get; set; }
        public string City { get; set; }
        public string Address { get; set; }
        public string Reward { get; set; }
        public string CreatorEmail { get; set; }
        public string MinRaiting { get; set; }
    }
}