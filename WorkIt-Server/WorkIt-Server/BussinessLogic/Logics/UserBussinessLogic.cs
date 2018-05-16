using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.ViewModels;

namespace WorkIt_Server.BussinessLogic.Logics
{
    public class UserBussinessLogic
    {
        private WorkItDbContext db;

        public UserBussinessLogic(WorkItDbContext db)
        {
            this.Db = db;
        }

        public WorkItDbContext Db
        {
            get
            {
                return this.db;
            }
            set
            {
                this.db = value;
            }
        }

        public ProfileDetailsViewModel getProfileDetails(int userId)
        {
            var user = db.Users.FirstOrDefault(u => u.UserId == userId);
            var picture = "asdf";

            return new ProfileDetailsViewModel
            {
                UserId = user.UserId,
                Email = user.Email,
                FullName = user.FullName,
                Phone = user.Phone,
                NumberOfReviewsAsSupervisor = user.ReviewsAsSupervisor,
                NumberOfReviewsAsTasker = user.ReviewsAsTasker,
                RatingAsSupervisor = user.RaitingAsSupervisor,
                RatingAsTasker = user.RaitingAsTasker,
                PictureAsString = picture
            };
        }
    }
}