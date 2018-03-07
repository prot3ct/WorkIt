using System;

namespace WorkIt_Server.Models.DTO
{
    public class TaskDTO
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public string Description { get; set; }
        public string Country { get; set; }
        public string City { get; set; }
        public string Address { get; set; }
        public string Reward { get; set; }
        public string CreatorEmail { get; set; }
        public string MinRaiting { get; set; }
        public string MinTasksCompleted { get; set; }
        public int? AssignedUserId { get; set; }

        public Location ToLocation()
        {
            return new Location
            {
                City = this.City,
                Country = this.Country,
                Address = this.Address
            };
        }
    }
}