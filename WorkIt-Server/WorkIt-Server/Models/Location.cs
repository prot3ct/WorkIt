using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WorkIt_Server.Models
{
    public class Location
    {
        [Key]
        public int LocationId { get; set; }

        [Required]
        [Index("IX_CountryCityAndAddress", 1, IsUnique = true)]
        [MaxLength(50)]
        public string Country { get; set; }

        [Required]
        [Index("IX_CountryCityAndAddress", 2, IsUnique = true)]
        [MaxLength(50)]
        public string City { get; set; }

        [Required]
        [Index("IX_CountryCityAndAddress", 3, IsUnique = true)]
        [MaxLength(50)]
        public string Address { get; set; }
    }
}