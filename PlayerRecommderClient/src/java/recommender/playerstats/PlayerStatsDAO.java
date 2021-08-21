/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommender.playerstats;

import fbref.jaxb.MiscellaneousStatsType;
import fbref.jaxb.PassingStatsType;
import fbref.jaxb.PlayerType;
import fbref.jaxb.ShootingStatsType;
import fbref.jaxb.StandardStatsType;
import fbref.jaxb.StatsType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import recommender.utils.ConnectionUtils;

/**
 *
 * @author KHANHBQSE63463
 */
public class PlayerStatsDAO {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public PlayerStatsDAO() {
    }

    public List<PlayerType> getList(int[] ids) throws SQLException {
        List<PlayerType> list = new ArrayList<>();
        for (int id : ids) {
            PlayerType type = getPlayerStats(id);
            list.add(type);
        }
        return list;
    }

    public PlayerType getPlayerStats(int id) throws SQLException {
        PlayerType player = new PlayerType();
        StatsType stats = new StatsType();
        StandardStatsType standard = new StandardStatsType();
        PassingStatsType passing = new PassingStatsType();
        ShootingStatsType shooting = new ShootingStatsType();
        MiscellaneousStatsType misc = new MiscellaneousStatsType();

        try {
            connection = ConnectionUtils.getConnection();
            if (connection != null) {
                String query = "SELECT * FROM playerstats WHERE id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);

                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    //get standard statsG
                    standard.setAssistsPer90(BigDecimal.valueOf(id));
                    standard.setSeasons(BigInteger.valueOf(resultSet.getInt("seasons")));
                    standard.setSquad(BigInteger.valueOf(resultSet.getInt("squad")));
                    standard.setCompLevel(BigInteger.valueOf(resultSet.getInt("comp_level")));
                    standard.setGames(BigInteger.valueOf(resultSet.getInt("games")));
                    standard.setGamesStarts(BigInteger.valueOf(resultSet.getInt("games_starts")));
                    standard.setMinutes(BigInteger.valueOf(resultSet.getInt("minutes")));
                    standard.setGoals(BigInteger.valueOf(resultSet.getInt("goals")));
                    standard.setAssists(BigInteger.valueOf(resultSet.getInt("assists")));
                    standard.setCardsYellow(BigInteger.valueOf(resultSet.getInt("cards_yellow")));
                    standard.setCardsRed(BigInteger.valueOf(resultSet.getInt("cards_red")));
                    standard.setGoalsPer90(BigDecimal.valueOf(resultSet.getFloat("goals_per90")));
                    standard.setAssistsPer90(BigDecimal.valueOf(resultSet.getFloat("assists_per90")));
                    standard.setGoalsAssistsPer90(BigDecimal.valueOf(resultSet.getFloat("goals_assists_per90")));
                    standard.setGoalsPer90(BigDecimal.valueOf(resultSet.getFloat("goals_pens_per90")));
                    standard.setGoalsAssistsPensPer90(BigDecimal.valueOf(resultSet.getFloat("goals_assists_pens_per90")));
                    standard.setXa(BigDecimal.valueOf(resultSet.getFloat("xa")));
                    standard.setXgPer90(BigDecimal.valueOf(resultSet.getFloat("xg_per90")));
                    standard.setXaPer90(BigDecimal.valueOf(resultSet.getFloat("xa_per90")));
                    standard.setXgXaPer90(BigDecimal.valueOf(resultSet.getFloat("xg_xa_per90")));
                    standard.setNpxgPer90(BigDecimal.valueOf(resultSet.getFloat("npxg_per90")));
                    standard.setNpxgXaPer90(BigDecimal.valueOf(resultSet.getFloat("npxg_xa_per90")));

                    //shooting stats
                    shooting.setPensMade(BigInteger.valueOf(resultSet.getInt("pens_made")));
                    shooting.setPensAtt(BigInteger.valueOf(resultSet.getInt("pens_att")));
                    shooting.setShotsTotal(BigInteger.valueOf(resultSet.getInt("shots_total")));
                    shooting.setShotsOnTarget(BigInteger.valueOf(resultSet.getInt("shots_on_target")));
                    shooting.setShotsFreeKicks(BigInteger.valueOf(resultSet.getInt("shots_free_kicks")));
                    shooting.setShotsOnTargetPct(BigDecimal.valueOf(resultSet.getFloat("shots_on_target_pct")));
                    shooting.setShotsTotalPer90(BigDecimal.valueOf(resultSet.getFloat("shots_total_per90")));
                    shooting.setShotsOnTargetPer90(BigDecimal.valueOf(resultSet.getFloat("shots_on_target_per90")));
                    shooting.setGoalsPerShot(BigDecimal.valueOf(resultSet.getFloat("goals_per_shot")));
                    shooting.setGoalsPerShotOnTarget(BigDecimal.valueOf(resultSet.getFloat("goals_per_shot_on_target")));
                    shooting.setXg(BigDecimal.valueOf(resultSet.getFloat("xg")));
                    shooting.setNpxg(BigDecimal.valueOf(resultSet.getFloat("npxg")));
                    shooting.setNpxgPerShot(BigDecimal.valueOf(resultSet.getFloat("npxg_per_shot")));
                    shooting.setXgNet(BigDecimal.valueOf(resultSet.getFloat("xg_net")));
                    shooting.setNpxg(BigDecimal.valueOf(resultSet.getFloat("npxg_net")));

                    //passing stats
                    passing.setXaNet(BigDecimal.valueOf(resultSet.getFloat("xa_net")));
                    passing.setAssistedShots(BigInteger.valueOf(resultSet.getInt("assisted_shots")));
                    passing.setPassesCompleted(BigInteger.valueOf(resultSet.getInt("passes_completed")));
                    passing.setPasses(BigInteger.valueOf(resultSet.getInt("passes")));
                    passing.setPassesPct(BigDecimal.valueOf(resultSet.getFloat("passes_pct")));
                    passing.setPassesPctShort(BigDecimal.valueOf(resultSet.getFloat("passes_completed_short")));
                    passing.setPassesShort(BigInteger.valueOf(resultSet.getInt("passes_short")));
                    passing.setPassesCompletedShort(BigInteger.valueOf(resultSet.getInt("passes_completed_short")));
                    passing.setPassesPctShort(BigDecimal.valueOf(resultSet.getFloat("passes_pct_short")));
                    passing.setPassesCompletedMedium(BigInteger.valueOf(resultSet.getInt("passes_completed_medium")));
                    passing.setPassesMedium(BigInteger.valueOf(resultSet.getInt("passes_medium")));
                    passing.setPassesPctMedium(BigDecimal.valueOf(resultSet.getFloat("passes_pct_medium")));
                    passing.setPassesCompletedLong(BigInteger.valueOf(resultSet.getInt("passes_completed_long")));
                    passing.setPassesLong(BigInteger.valueOf(resultSet.getInt("passes_long")));
                    passing.setPassesPctLong(BigDecimal.valueOf(resultSet.getFloat("passes_pct_long")));
                    passing.setPassesLeftFoot(BigInteger.valueOf(resultSet.getInt("passes_left_foot")));
                    passing.setPassesRightFoot(BigInteger.valueOf(resultSet.getInt("passes_right_foot")));
                    passing.setPassesFreeKicks(BigInteger.valueOf(resultSet.getInt("passes_free_kicks")));
                    passing.setThroughBalls(BigInteger.valueOf(resultSet.getInt("through_balls")));
                    passing.setCornerKicks(BigInteger.valueOf(resultSet.getInt("corner_kicks")));
                    passing.setThrowIns(BigInteger.valueOf(resultSet.getInt("throw_ins")));
                    passing.setPassesIntoFinalThird(BigInteger.valueOf(resultSet.getInt("passes_into_final_third")));
                    passing.setPassesIntoPenaltyArea(BigInteger.valueOf(resultSet.getInt("passes_into_penalty_area")));
                    passing.setCrossesIntoPenaltyArea(BigInteger.valueOf(resultSet.getInt("crosses_into_penalty_area")));

                    // miscelaneous stats
                    misc.setCardsYellowRed(BigInteger.valueOf(resultSet.getInt("cards_yellow_red")));
                    misc.setFouls(BigInteger.valueOf(resultSet.getInt("fouls")));
                    misc.setFouled(BigInteger.valueOf(resultSet.getInt("fouled")));
                    misc.setOffsides(BigInteger.valueOf(resultSet.getInt("offsides")));
                    misc.setCrosses(BigInteger.valueOf(resultSet.getInt("crosses")));
                    misc.setTacklesWon(BigInteger.valueOf(resultSet.getInt("tackles_won")));
                    misc.setInterceptions(BigInteger.valueOf(resultSet.getInt("interceptions")));
                    misc.setPensWon(BigInteger.valueOf(resultSet.getInt("pens_won")));
                    misc.setPensConceded(BigInteger.valueOf(resultSet.getInt("pens_conceded")));
                    misc.setOwnGoals(BigInteger.valueOf(resultSet.getInt("own_goals")));
                    misc.setDribblesCompleted(BigInteger.valueOf(resultSet.getInt("dribbles_completed")));
                    misc.setDribbles(BigInteger.valueOf(resultSet.getInt("dribbles")));
                    misc.setDribblesCompletedPct(BigDecimal.valueOf(resultSet.getFloat("dribbles_completed_pct")));
                    misc.setPlayersDribbledPast(BigInteger.valueOf(resultSet.getInt("players_dribbled_past")));
                    misc.setNutmegs(BigInteger.valueOf(resultSet.getInt("nutmegs")));
                    misc.setDribbleTackles(BigInteger.valueOf(resultSet.getInt("dribble_tackles")));
                    misc.setDribblesVs(BigInteger.valueOf(resultSet.getInt("dribbles_vs")));
                    misc.setDribbleTacklesPct(BigDecimal.valueOf(resultSet.getFloat("dribble_tackles_pct")));
                    misc.setDribbledPast(BigInteger.valueOf(resultSet.getInt("dribbled_past")));

                    stats.setMiscellaneousStats(misc);
                    stats.setShootingStats(shooting);
                    stats.setPassingStats(passing);
                    stats.setStandardStats(standard);

                    player.setStats(stats);
                    player.setId(BigInteger.valueOf(id));
                    return player;
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(PlayerStatsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return null;
    }

    private void closeConnection() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
