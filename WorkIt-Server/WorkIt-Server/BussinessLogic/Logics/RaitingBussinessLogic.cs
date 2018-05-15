using System.Collections.Generic;
using System.Linq;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BussinessLogic.Logics
{
    public class RaitingBussinessLogic
    {
        private WorkItDbContext db;

        public RaitingBussinessLogic(WorkItDbContext db)
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

        public void CreateRatiing(CreateRaitingDTO raiting)
        {
            var raitingToBeInserted = new Raiting
            {
                Value = raiting.Value,
                Description = raiting.Description,
                ReceiverUserId = raiting.ReceiverUserId,
                TaskId = raiting.TaskId,
                ReceiverUserRoleId = raiting.ReceiverUserRoleId
            };

            db.Raitings.Add(raitingToBeInserted);
            db.SaveChanges();
        }

        public IEnumerable<CreateRaitingDTO> GetAllRaitingByUserId(int userId)
        {
            var raitings = Db.Raitings
                .Where(r => r.ReceiverUserId == userId)
                .Select(r => new CreateRaitingDTO
                {
                    Value = r.Value,
                    ReceiverUserId = r.ReceiverUserId,
                    ReceiverUserRoleId = r.ReceiverUserRoleId,
                    TaskId = r.TaskId
                })
                .ToList();

            return raitings;
        }
    }
}